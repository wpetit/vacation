// Create a controller with name VacationsCalendarCtrl to bind to the html page.
vacationAppControllers
		.controller(
				'VacationCalendarCtrl',
				function($scope, $location, $routeParams, $modal, toaster,
						vacationService, vacationTypeService) {
					
					/* config object */
				    $scope.uiConfig = {
				      calendar:{
				        height: 450,
				        editable: true,
				        header:{
				          left: 'title',
				          center: '',
				          right: 'today prev,next'
				        },
//				        eventClick: $scope.alertOnEventClick,
//				        eventDrop: $scope.alertOnDrop,
//				        eventResize: $scope.alertOnResize,
//				        eventRender: $scope.eventRender
				      }
				    };
					
					
				});
