var app = angular.module('vacationApp', [ 'ngRoute', 'ui.calendar', 'ui.bootstrap',
		'vacationAppControllers', 'vacationAppFilters', 'toaster' ]);

var vacationAppControllers = angular.module('vacationAppControllers', []);

var vacationAppFilters = angular.module('vacationAppFilters', []);

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/vacation/list', {
		templateUrl : 'partials/vacation-list.html',
		controller : 'VacationsCtrl'
	}).when('/vacation/create', {
		templateUrl : 'partials/vacation-create.html',
		controller : 'VacationsCtrl'
	}).when('/vacation/update/:vacationId', {
		templateUrl : 'partials/vacation-update.html',
		controller : 'VacationsCtrl'
	}).when('/vacationTypes/create', {
		templateUrl : 'partials/vacation-type-create.html',
		controller : 'VacationTypeCtrl'
	}).when('/vacationTypes/update/:vacationTypeId', {
		templateUrl : 'partials/vacation-type-update.html',
		controller : 'VacationTypeCtrl'
	}).when('/vacationTypes/list', {
		templateUrl : 'partials/vacation-type-list.html',
		controller : 'VacationTypeCtrl'
	}).when('/vacation/calendar', {
		templateUrl : 'partials/vacation-calendar.html',
		controller : 'VacationCalendarCtrl'
	}).otherwise({
		redirectTo : '/vacation/list'
	});
} ]);