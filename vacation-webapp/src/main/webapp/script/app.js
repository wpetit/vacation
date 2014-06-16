var app = angular
		.module('vacationApp', [ 'ngRoute', 'ngGrid', 'ui.bootstrap', 'vacationAppControllers' ]);

var vacationAppControllers = angular.module('vacationAppControllers', []);


app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/vacations', {
		templateUrl : 'partials/vacation-list.html',
		controller : 'VacationsCtrl'
	}).when('/vacationTypes', {
		templateUrl : 'partials/vacation-type-list.html',
		controller : 'VacationTypeCtrl'
	}).
	otherwise({
		redirectTo : '/vacations'
	});
} ]);