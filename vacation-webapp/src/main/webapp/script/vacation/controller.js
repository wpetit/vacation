// Create a controller with name VacationsCtrl to bind to the html page.
vacationAppControllers
		.controller(
				'VacationsCtrl',
				function($scope, $location, $routeParams, $modal, toaster,
						vacationService, vacationTypeService) {

					// Initialization
					$scope.minDate = new Date();
					$scope.from = new Date();
					$scope.to = new Date();
					$scope.dateFormat = 'dd-MM-yyyy';
					$scope.fromMorning=true;
					$scope.toMorning=false;
					// paging
					$scope.pagingOptions = {
						pageSize : 5,
						currentPage : 1
					};

					// sort
					$scope.sortOptions = {
						field : "from",
						direction : "desc"
					};

					// dateOptions
					$scope.dateOptions = {
						formatYear : 'yyyy',
						startingDay : 1
					};

					// Refresh vacations with paging and sort options.
					$scope.refresh = function() {
						var page = $scope.pagingOptions.currentPage;
						vacationService.getAll(
								($scope.pagingOptions.pageSize * (page - 1)),
								5, $scope.sortOptions.field,
								$scope.sortOptions.direction).success(
								function(data) {
									$scope.modelList = data.modelList;
									$scope.nbResults = data.total;
								});
					};

					// Sort vacations
					$scope.sortBy = function(attribute) {
						if ($scope.sortOptions.field != attribute) {
							$scope.sortOptions = {
								field : attribute,
								direction : "desc"
							};
						} else {
							if ($scope.sortOptions.direction == "desc") {
								$scope.sortOptions.direction = "asc";
							} else {
								$scope.sortOptions.direction = "desc";
							}
						}
					};

					// Refresh vacations when sortOptions changes.
					$scope.$watch('sortOptions', function(newVal, oldVal) {
						if (newVal !== oldVal) {
							$scope.refresh();
						}
					}, true);

					// Go to create vacation screen.
					$scope.goToCreateVacation = function() {
						$location.path('/vacation/create');
					};

					// Go to update vacation screen.
					$scope.goToUpdateVacation = function(id) {
						$location.path('vacation/update/' + id);
					};

					// Get the vacation given.
					$scope.getVacation = function(id) {
						vacationService.get(id).success(function(data) {
							$scope.minDate = data.from;
							$scope.id = data.id;
							$scope.from = data.from;
							$scope.fromMorning = (new Date(data.from).getHours() == 0);
							$scope.to = data.to;
							$scope.toMorning = (new Date(data.to).getHours() == 12);
							$scope.typeId = data.type.id;
						});
					};

					// Save the vacation.
					$scope.save = function() {
						var fromWithTime = $scope.from;
						if($scope.fromMorning) {
							fromWithTime.setHours(0);
							fromWithTime.setMinutes(0);
							fromWithTime.setSeconds(1);
						} else {
							fromWithTime.setHours(12);
							fromWithTime.setMinutes(0);
							fromWithTime.setSeconds(0);
						}
						
						var toWithTime = $scope.to;
						if($scope.toMorning) {
							toWithTime.setHours(12);
							toWithTime.setMinutes(0);
							toWithTime.setSeconds(0);
						} else {
							toWithTime.setHours(23);
							toWithTime.setMinutes(59);
							toWithTime.setSeconds(58);
						}
						vacationService.save(fromWithTime, toWithTime,
								$scope.typeId).success(
								function(data) {
									$scope.from = null;
									$scope.to = null;
									toaster.pop('success',
											"Vacation successfully saved.");
								});
					};

					// Update the vacation.
					$scope.update = function() {
						var fromWithTime = $scope.from;
						if($scope.fromMorning) {
							fromWithTime.setHours(0);
							fromWithTime.setMinutes(0);
							fromWithTime.setSeconds(1);
						} else {
							fromWithTime.setHours(12);
							fromWithTime.setMinutes(0);
							fromWithTime.setSeconds(0);
						}
						
						var toWithTime = $scope.to;
						if($scope.toMorning) {
							toWithTime.setHours(12);
							toWithTime.setMinutes(0);
							toWithTime.setSeconds(0);
						} else {
							toWithTime.setHours(23);
							toWithTime.setMinutes(59);
							toWithTime.setSeconds(58);
						}
						
						vacationService.update($scope.id, fromWithTime,
								toWithTime, $scope.typeId).success(
								function(data) {
									$location.path('vacation/list');
									toaster.pop('success',
											"Vacation successfully updated.");
								});
					};

					// Delete the vacation.
					$scope.deleteVacation = function(id) {
						var modalInstance = $modal
								.open({
									templateUrl : 'templates/confirmationDialog.html',
									controller : DialogCtrl,
									size : 'md',
									resolve : {
										title : function() {
											return "Confirmation";
										},
										message : function() {
											return "The vacation will be deleted. Continue ?";
										}
									}
								});

						modalInstance.result
								.then(
										function() {
											vacationService
													.deleteVacation(id)
													.success(
															function(data) {
																$scope.from = null;
																$scope.to = null;
																toaster
																		.pop(
																				'success',
																				"Vacation successfully deleted.");
																// Get vacations
																$scope
																		.refresh();
																// Get vacation
																// types
																// balances
																$scope
																		.retrieveVacationTypes();
															});
										}, function() {
										});
					};

					// Cancel. Go back to vacation list.
					$scope.cancel = function() {
						$location.path('/vacation/list');
					};

					// Disable weekend selection
					$scope.disabled = function(date, mode) {
						return (mode === 'day' && (date.getDay() === 0 || date
								.getDay() === 6));
					};

					// Open from datePicker
					$scope.openFromDatePicker = function($event) {
						$event.preventDefault();
						$event.stopPropagation();
						$scope.fromDatePickerOpened = true;
					};

					// Open to datePicker
					$scope.openToDatePicker = function($event) {
						$event.preventDefault();
						$event.stopPropagation();
						$scope.toDatePickerOpened = true;
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

					// Initialization
					if ($routeParams.vacationId == null) {
						// Get vacations
						$scope.refresh();
						// Get vacation types balances
						$scope.retrieveVacationTypes();
					} else {
						$scope.getVacation($routeParams.vacationId);
						$scope.retrieveVacationTypes();
					}

				});
