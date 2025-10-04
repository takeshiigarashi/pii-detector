from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import spacy

nlp_ja_ginza_electra = spacy.load("ja_ginza_electra")
nlp_ja_ginza = spacy.load("ja_ginza")

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
      })

   return {"tokens": results}

