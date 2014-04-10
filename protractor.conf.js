require('coffee-script').register();

exports.config = {

  seleniumAddress: "http://localhost:4444/wd/hub",

  specs: [
    'src/test/protractor/e2e.coffee'
  ],

  baseUrl: 'http://localhost:8080',

  onPrepare: function () {
    global.By = global.by;
  }
};

