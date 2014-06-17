var app = angular.module('vacationApp', [ 'ngRoute', 'ui.bootstrap',
		'vacationAppControllers', 'vacationAppFilters' ]);

var vacationAppControllers = angular.module('vacationAppControllers', []);

var vacationAppFilters = angular.module('vacationAppFilters', []);

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/list', {
		templateUrl : 'partials/vacation-list.html',
		controller : 'VacationsCtrl'
	}).when('/vacationTypes', {
		templateUrl : 'partials/vacation-type-list.html',
		controller : 'VacationTypeCtrl'
	}).otherwise({
		redirectTo : '/list'
	});
} ]);