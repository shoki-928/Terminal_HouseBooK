# Terminal_HouseBooK
### 概要  
ターミナルベースの家計簿です

### 作成理由  
自分で家計簿を使った際、アプリを起動する時間やカレンダー上で数値を書き込み保存。  
以上の流れが個人的に面倒だと感じたため、高速に起動できて使用場所と数値を入力するだけで済む家計簿が欲しかった為。

### 機能  
起動後、使用場所と使用金額の入力を求める。  
入力されたデータを外部ファイルに保存。  
過去に入力されたデータも含めてターミナルに綺麗に揃えて出力する。  
また、起動時に月を跨いでいた場合自動でその月のファイルを作成する。  

### 意識したこと  
Mainクラスに書くコードを少なくする為に処理のクラスは別でかくことによりコードの視認性が確保された。  
修正後のコードでは各データ(使用場所、使用金額、日付)を別ファイルで管理することによりコード上での処理が楽になった。  

### 学び  
コードレビュー後、変数命名の際の規則性やArrayListをfor文で回す際の存在チェックなど多くご指摘いただき大変勉強になりました。  
今までなんとなくで理解していた引数の型指定などへの理解が深まった。

### 使用後の課題  
使用場所や金額などをターミナル上に綺麗に出力する際、文字数-担保したい文字数の計算をして平等に空白を入れるようにしていたが日本語文字が入るとずれてしまう。  
これをFormatなどへの理解を深めて扱えるようにしたい。  
また、このようなデータを扱う際に関しては次はDB(SQL)などへの理解を深めて使用したい。
