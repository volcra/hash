'use strict';

/* App Module */

define(['angular'], function (angular) {
  return angular
    .module('app', ['app.directives'])
    .config(['$routeProvider', function($routeProvider) {
      $routeProvider
        .when('/home', { templateUrl: 'views/home.html' })
        .otherwise({ redirectTo: '/home' });
    }])
});
