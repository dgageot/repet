describe 'E2E: Acceptance Testing', ->
  beforeEach ->
    browser.get '/'

  it 'should have a working welcome page', ->
    expect($('h1').getText()).toEqual 'Hello World!'
    expect(element.all(By.css('li')).count()).toBe 2
