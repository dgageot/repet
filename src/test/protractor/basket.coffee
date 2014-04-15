describe 'End to end test', ->
  beforeEach ->
    browser.get '/'

  it 'should update the basket with two developers', ->
    find('#clear').click()
    find('#David .btn-success').click()
    find('#Mathilde .btn-success').click()

    expect(find('#basket .text-right').getText()).toContain '1700'

  it 'should find the right number of front skill for dgageot', ->
    find('#clear').click()
    find('#David .btn-success').click()
    element.all(By.css('.box.front:not(.ng-hide)')).then (array) -> expect(array.length).toEqual 2
    element.all(By.css('.box.back:not(.ng-hide)')).then (array) -> expect(array.length).toEqual 1
    element.all(By.css('.box.database:not(.ng-hide)')).then (array) -> expect(array.length).toEqual 0
    element.all(By.css('.box.test:not(.ng-hide)')).then (array) -> expect(array.length).toEqual 1
    element.all(By.css('.box.hipster:not(.ng-hide)')).then (array) -> expect(array.length).toEqual 1