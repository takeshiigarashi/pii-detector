from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import spacy
from spacy.tokens import Token
from spacy.language import Language
import re

nlp_ja_ginza_electra = spacy.load("ja_ginza_electra")
nlp_ja_ginza = spacy.load("ja_ginza")

Token.set_extension("is_credit_card", default=False)
Token.set_extension("is_email", default=False)
Token.set_extension("is_phone_number", default=False)

pattern_credit_card = re.compile(r"\d{4}-\d{4}-\d{4}-\d{4}")
pattern_email = re.compile(r"[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}")
pattern_phone = re.compile(r"0\d{1,4}-\d{1,4}-\d{4}")

@Language.component("mark_credit_card")
def mark_credit_card(doc):
    for match in pattern_credit_card.finditer(doc.text):
        span = doc.char_span(match.start(), match.end())
        if span:
            for token in span:
                token._.is_credit_card = True
    return doc

@Language.component("mark_email")
def mark_email(doc):
    for match in pattern_email.finditer(doc.text):
        span = doc.char_span(match.start(), match.end())
        if span:
            for token in span:
                token._.is_email = True
    return doc

@Language.component("mark_phone_number")
def mark_phone_number(doc):
    for match in pattern_phone.finditer(doc.text):
        span = doc.char_span(match.start(), match.end())
        if span:
            for token in span:
                token._.is_phone_number = True
    return doc

nlp_ja_ginza.add_pipe("mark_credit_card", last=True)
nlp_ja_ginza.add_pipe("mark_email", last=True)
nlp_ja_ginza.add_pipe("mark_phone_number", last=True)  

nlp_ja_ginza_electra.add_pipe("mark_credit_card", last=True)
nlp_ja_ginza_electra.add_pipe("mark_email", last=True)
nlp_ja_ginza_electra.add_pipe("mark_phone_number", last=True)

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
      results.append({
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
         "is_credit_card": token._.is_credit_card,
         "is_email": token._.is_email,
         "is_phone_number": token._.is_phone_number,
      })

   return {"tokens": results}

