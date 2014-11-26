// Create a controller with name VacationsCalendarCtrl to bind to the html page.
vacationAppControllers
		.controller(
				'VacationCalendarCtrl',
				function($scope, $location, $routeParams, $modal, toaster,
						vacationService, vacationTypeService) {
					
					var date = new Date();
				    var d = date.getDate();
				    var m = date.getMonth();
				    var y = date.getFullYear();
					
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
				    
				    /* event source that contains custom events on the scope */
				    $scope.events = new Array();
				    $scope.eventsF= new Array();
				    
				    $scope.getAll = function() { {
							var page = 0;
							vacationService.getAll(
									0,
									2000, 'from',
									'desc').success(
									function(data) {
										$scope.vacations = data.modelList;
										$scope.vacations.forEach(function(vacation) {
											endDate = new Date(vacation.to);
											endDate.setDate(endDate.getDate()+1);
											$scope.events.push({
										        title: vacation.type.type,
										        start: new Date(vacation.from),
										        end: endDate,
										        allDay: true
										      });
										});
									});
						};
				    };
				    
				    // Get vacation types
					$scope.retrieveVacationTypes = function() {
						vacationTypeService.getVacationTypeList().success(
								function(data) {
									$scope.vacationTypes = data;
									$scope.numberOfVacations = new Array();
									data.forEach(function(vacationType) {
										$scope.retrieveVacationCount(
												vacationType.id,
												vacationType.type,
												vacationType.numberOfDays);
									});
								});
					};

					// Get vacation balances.
					$scope.retrieveVacationCount = function(vacationTypeId,
							vacationTypeName, max) {
						vacationService.getNumberOfVacations(vacationTypeId)
								.success(function(data) {
									$scope.numberOfVacations.push({
										vacationTypeId : vacationTypeId,
										vacationTypeName : vacationTypeName,
										max : max,
										count : data
									});
								});
					};
				    
				    $scope.eventSources = [$scope.events,$scope.eventsF];
				    $scope.getAll();
					
				    $scope.retrieveVacationTypes();
				    
				    /* event source that calls a function on every view switch */
				    $scope.eventsF = function (start, end, timezone, callback) {
				      var s = new Date(start).getTime() / 1000;
				      var e = new Date(end).getTime() / 1000;
				      var m = new Date(start).getMonth();
				      var events = [{title: 'Feed Me ' + m,start: s + (50000),end: s + (100000),allDay: false, className: ['customFeed']}];
				      callback(events);
				    };
					
				});
