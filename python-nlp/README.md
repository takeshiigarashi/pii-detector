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

テストデータ

```
いつもお世話になっております。
東京都新宿区西新宿2-8-1に住んでいる山田太郎と申します。
先日、御社のオンラインショップで注文した商品がまだ届いていないためご連絡いたしました。
注文番号は123456789です。
登録している電話番号は090-1234-5678、メールアドレスはtaro.yamada@example.comです。
支払いにはクレジットカード（番号は4111-1111-1111-1111、有効期限は12/27）を利用しました。
配送状況を確認したところ「発送済み」と表示されていますが、自宅には届いていません。
また、家族の代理で受け取りができるようになっているかどうかも知りたいです。
念のため、本人確認のために生年月日（1980年5月15日）もお伝えいたしますので、ご確認のうえ折り返しご連絡いただけますでしょうか。
```

パッケージのアンインストール

```
pip freeze > requirements.txt
pip uninstall -y -r requirements.txt
rm requirements.txt
```

uvicornによるfastapiサーバー起動

```

```

