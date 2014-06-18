var app = angular.module('vacationApp', [ 'ngRoute', 'ui.bootstrap',
		'vacationAppControllers', 'vacationAppFilters' ]);

var vacationAppControllers = angular.module('vacationAppControllers', []);

var vacationAppFilters = angular.module('vacationAppFilters', []);

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/vacation/list', {
		templateUrl : 'partials/vacation-list.html',
		controller : 'VacationsCtrl'
	}).when('/vacation/create', {
		templateUrl : 'partials/vacation-create.html',
		controller : 'VacationsCtrl'
	}).when('/vacationTypes/create', {
		templateUrl : 'partials/vacation-type-create.html',
		controller : 'VacationTypeCtrl'
	}).when('/vacationTypes/list', {
		templateUrl : 'partials/vacation-type-list.html',
		controller : 'VacationTypeCtrl'
	}).otherwise({
		redirectTo : '/vacation/list'
	});
} ]);