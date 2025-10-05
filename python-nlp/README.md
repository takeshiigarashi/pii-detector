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

```
いつでもどうぞ。以下はプレーンテキスト形式のサンプル群です（そのままコピーして textarea に貼ってテストできます）。

お問い合わせは [info@example.com](mailto:info@example.com) までお願いします。
連絡先: [taro.yamada@company.co.jp](mailto:taro.yamada@company.co.jp)
Please email [john_doe-123@mail-service.net](mailto:john_doe-123@mail-service.net) for more info.
送信先：[support@my-site.io](mailto:support@my-site.io) です。
メール: [test.user+temp@sub.domain.org](mailto:test.user+temp@sub.domain.org)

電話番号は090-1234-5678です。
会社の番号：03-1234-5678
海外オフィス +1-202-555-0198 に連絡してください。
連絡先（TEL）: 011-123-4567
TEL: 08012345678（担当：山田）

クレジットカード番号は 1234-5678-9012-3456 です。
VISA: 4111-1111-1111-1111 有効期限12/27
カード情報：5500-0000-0000-0004
My credit card is 6011-2345-6789-0123.
Card#: 3530-1111-2222-3333

山田太郎（東京都港区南青山1-2-3）まで郵送してください。
Name: Hanako Suzuki, Address: 1-2-3 Shibuya, Tokyo 150-0002
配送先：〒160-0022 東京都新宿区新宿3-4-5
Please send it to Michael Brown at 1234 Elm Street, Boston, MA.
顧客ID: 12345678, 名前: 佐藤 健, 住所: 名古屋市中区1-2-3

田中さん（[tanaka@example.com](mailto:tanaka@example.com) / 080-9999-8888）に連絡してください。
Billing info: VISA 4111-1111-1111-1111, Email [john@shop.com](mailto:john@shop.com)
My phone +44 20 7946 0958, email: [sam.jones@mail.co.uk](mailto:sam.jones@mail.co.uk)
連絡先：08012345678、メール：[example_user@domain.jp](mailto:example_user@domain.jp)
注文者：[yuki@example.com](mailto:yuki@example.com)、カード：1234-5678-9012-3456

お問い合わせフォームから送信しました: [user.name+alias@sub.example.co.jp](mailto:user.name+alias@sub.example.co.jp)
緊急連絡先は 050-1234-5678 までお願いします。
カード番号（ハイフンなし）4111111111111111 を使いました。
請求先住所：大阪府大阪市北区梅田1-1-1 マンション101
補足: Passport number PX1234567 は別途提出します。

会社代表メール: [contact@enterprise.co.jp](mailto:contact@enterprise.co.jp)
SMSで送った確認コードは 012345 です（サンプル）。
配送希望時間は「午前中」、受取人：山本 隆（090-2222-3333）
海外配送先: 10 Downing St, London SW1A 2AA
[support-team@service-example.org](mailto:support-team@service-example.org) に転送済み

必要なら、このプレーンテキストに加えて「ラベル付き（各行に type:EMAIL/PHONE/CARD/... を付けた形式）」も作成しますか？
```

パッケージのアンインストール

```
pip freeze > requirements.txt
pip uninstall -y -r requirements.txt
rm requirements.txt
```

画面イメージ

[](./screen.png)
