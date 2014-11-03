angular.module 'devoxx', ['ngAnimate']

.controller 'BasketController', class
  constructor: (@$http)->
    @basket = {}
    @emails = []

  add: (email) ->
    @emails.push email unless email in @emails
    @refresh()

  remove: (email) ->
    @emails = (mail for mail in @emails when mail isnt email)
    @refresh()

  refresh: ->
    @$http.get("/basket?emails=#{@emails}").success (data) =>
      @basket = data

  shouldShowRemoveButton: (email) ->
    email in @emails

.directive 'score', ->
  scope:
    value: '='
    category: '@'
  templateUrl: '/directives/score.html'
