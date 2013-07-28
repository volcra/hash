'use strict';

/* Controllers */

define(['app'], function (app) {
  app.controller('MenuCtrl', ['$scope', '$location', function ($scope, $location) {
    $scope.menu = {
      items: [{
          text: 'Home', url: '#/home', target: '_self'
        }
      ]
    }

    $scope.getMenuItemClassStyle = function (item) {
      return $location.url().search(item.url.replace('#', '')) == 0 ? 'active' : '';
    }
  }]);
});
