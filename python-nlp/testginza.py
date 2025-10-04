import spacy

# transformer版のja_ginza
nlp = spacy.load("ja_ginza_electra")

text = "私は昨日東京に行きました"
doc = nlp(text)

for token in doc:
    print(token.text, token.lemma_, token.pos_, token.dep_, token.head.text)
