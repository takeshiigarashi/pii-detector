# README.md

## インストール手順

numpy、spacy、thincのバージョンを固定する必要があった。

まず、constraints.txtを以下の内容で作成した。

```constraints.txt
numpy<2
thinc<8.4
spacy<3.9
```

続いて、constraints.txtを指定して、numpyをインストール。

```
pip install -U numpy -c constrants.txt
```

続けて、ginzaをインストール

```
pip install -U ginza ja_ginza ja_ginza_electra
```

uvicorn、fastapiのインストール

```
pip install fastapi uvicorn
```

APIサーバーの起動

```
uvicorn main:app --reload
```

## メモ

パッケージのアンインストール

```
pip freeze > requirements.txt
pip uninstall -y -r requirements.txt
rm requirements.txt
```

uvicornによるfastapiサーバー起動

```

```

