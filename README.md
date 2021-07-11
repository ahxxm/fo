# fo

A Clojure library designed to ... pass message.

Credits: [code](https://github.com/lersh/TudouCode/blob/master/TudouSharp/Tudou.cs), [index.html](http://www.keyfc.net/bbs/tools/tudoucode.aspx)

## Usage

[与佛论禅](https://folol.herokuapp.com/)

## Deploy

```bash
lein uberjar

wget https://cli-assets.heroku.com/heroku-linux-x64.tar.gz
tar xf heroku-linux-x64.tar.gz

# ./heroku/bin/heroku stack:set heroku-20 -a folol
./heroku/bin/heroku login
./heroku/bin/heroku deploy:jar target/fo-0.1.0-SNAPSHOT-standalone.jar --app folol
```

## License

WTFPL
