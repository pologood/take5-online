'use strict';

/* Controllers */

var controllers = angular.module('take5Controllers.home', []);

controllers.controller('HomeCtrl', [
		'$scope',
		'WebSocketManagerService',
		'ACTION',
		'$state',
		function($scope, WebSocketManagerService, ACTION, $state) {
			$scope.game = {};

			var checkListLobbiesResult = function(data) {
				$scope.game.lobbies = data.lobbies;
			};
			var checkListUsersResult = function(data) {
				$scope.game.users = data.users;
			};

			WebSocketManagerService.register(ACTION.LIST_LOBBIES).then(
					checkListLobbiesResult);
			WebSocketManagerService.register(ACTION.LIST_USERS).then(
					checkListUsersResult);

			WebSocketManagerService.send({
				action : ACTION.LIST_LOBBIES
			});
			
			WebSocketManagerService.send({
				action : ACTION.LIST_USERS
			});

		} ]);