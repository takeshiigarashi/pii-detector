from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import spacy
from spacy.tokens import Token
from spacy.language import Language
import json
import re

# 正規表現設定を読み込む
with open("patterns.json", "r") as f:
    pattern_configs = json.load(f)

# Token拡張を設定
for config in pattern_configs:
    Token.set_extension(config["extension_key"], default=False)

# コンポーネントを動的に作成
@Language.component("mark_pii_extension")
def mark_pii_extension(doc):
    for config in pattern_configs:
        for pattern in config["patterns"]:
            compiled_pattern = re.compile(pattern)
            for match in compiled_pattern.finditer(doc.text):
                span = doc.char_span(match.start(), match.end())
                if span:
                    for token in span:
                        setattr(token._, config["extension_key"], True)
    return doc

# モデルにコンポーネントを追加
nlp_ja_ginza = spacy.load("ja_ginza")
nlp_ja_ginza.add_pipe("mark_pii_extension", last=True)

nlp_ja_ginza_electra = spacy.load("ja_ginza_electra")
nlp_ja_ginza_electra.add_pipe("mark_pii_extension", last=True)

app = FastAPI()

class TextRequest(BaseModel):
    model: str = "ja_ginza"  # デフォルトモデルをja_ginzaに設定
    text: str

@app.get("/")
def read_root():
    return {"message": "GiNZA API is running"}

@app.post("/analyze")
def analyze_text(request: TextRequest):
    """
    GiNZAで形態素解析を行うAPI
    """
    if request.model == "ja_ginza":
        nlp = nlp_ja_ginza
    elif request.model == "ja_ginza_electra":
        nlp = nlp_ja_ginza_electra
    else:
        raise HTTPException(status_code=400, detail="Invalid model specified. Use 'ja_ginza' or 'ja_ginza_electra'.")
    
    doc = nlp(request.text)
    results = []
    for token in doc:
        token_data = {
            "surface": token.text,
            "lemma": token.lemma_,
            "idx": token.idx,
            "pos": token.pos_,
            "tag": token.tag_,
            "ent_type": token.ent_type_,
            "is_alpha": token.is_alpha,
            "is_digit": token.is_digit,
            "is_punct": token.is_punct,
            "is_stop": token.is_stop,
            "dpendency": token.dep_,
            "head": token.head.text,
        }
        for config in pattern_configs:
            token_data[config["extension_key"]] = getattr(token._, config["extension_key"])
        results.append(token_data)

    return {"tokens": results}

