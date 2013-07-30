'use strict';

/* Controllers */

define(['app'], function (app) {
  app.controller('MenuCtrl', ['$scope', '$location', function ($scope, $location) {
    $scope.menu = {
      items: []
    }

    $scope.getMenuItemClassStyle = function (item) {
      return $location.url().search(item.url.replace('#', '')) == 0 ? 'active' : '';
    }
  }]);
});
