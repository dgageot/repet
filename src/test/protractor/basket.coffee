describe 'End to end test', ->
  beforeEach ->
    browser.get '/'

  it 'should update the basket with two developers', ->
    find('#clear').click()
    find('#David .btn-success').click()
    find('#Mathilde .btn-success').click()

    expect(find('#basket .text-right').getText()).toContain '1700'
