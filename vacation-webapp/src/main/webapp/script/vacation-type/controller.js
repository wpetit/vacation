// Create a controller with name VacationsCtrl to bind to the html page.
vacationAppControllers
		.controller(
				'VacationTypeCtrl',
				function($scope, $location, $routeParams, $modal, toaster,
						vacationService, vacationTypeService) {

					$scope.openBeginDatePicker = function($event) {
						$event.preventDefault();
						$event.stopPropagation();
						$scope.beginDatePickerOpened = true;
					};

					$scope.openEndDatePicker = function($event) {
						$event.preventDefault();
						$event.stopPropagation();
						$scope.endDatePickerOpened = true;
					};

					// Save the vacation type.
					$scope.save = function() {
						var beginDateWithTime = $scope.beginDate;
						beginDateWithTime.setHours(0);
						beginDateWithTime.setMinutes(0);
						beginDateWithTime.setSeconds(0);
						var endDateWithTime = $scope.endDate;
						endDateWithTime.setHours(23);
						endDateWithTime.setMinutes(59);
						endDateWithTime.setSeconds(59);
							
						vacationTypeService
								.saveVacationType($scope.typeName,
										beginDateWithTime, endDateWithTime,
										$scope.numberOfDays)
								.success(
										function(data) {
											$scope.typeName = null;
											$scope.beginDate = new Date();
											$scope.endDate = new Date();
											$scope.numberOfDays = 1;
											toaster
													.pop('success',
															"Vacation type successfully saved.");
										});
					};

					// Cancel the current action. Go back to list.
					$scope.cancel = function() {
						$location.path('/vacationTypes/list');
					};

					// Get the vacationType with oldTypeId
					$scope.getVacationType = function() {
						vacationTypeService.getVacationType($scope.typeId)
								.success(function(data) {
									$scope.id = data.id;
									$scope.typeName = data.type;
									$scope.beginDate = data.beginDate;
									$scope.endDate = data.endDate;
									$scope.numberOfDays = data.numberOfDays;
								});
					};

					// Get all vacation types with sort.
					$scope.getAll = function() {
						vacationTypeService.getVacationTypeList(
								$scope.sortOptions.field,
								$scope.sortOptions.direction).success(
								function(data) {
									$scope.vacationTypes = data;
								});
					};

					// Update vacation type.
					$scope.update = function() {
						var beginDateWithTime = $scope.beginDate;
						beginDateWithTime.setHours(0);
						beginDateWithTime.setMinutes(0);
						beginDateWithTime.setSeconds(0);
						var endDateWithTime = $scope.endDate;
						endDateWithTime.setHours(23);
						endDateWithTime.setMinutes(59);
						endDateWithTime.setSeconds(59);
						
						vacationTypeService
								.updateVacationType($scope.id, $scope.typeName,
										beginDateWithTime, endDateWithTime,
										$scope.numberOfDays)
								.success(
										function(data) {
											toaster
													.pop('success',
															"Vacation type successfully updated.");
										});
					};

					$scope.deleteVacationType = function(id) {
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
											return "The vacation type and all vacations of this type will be deleted. Continue ?";
										}
									}
								});

						modalInstance.result
								.then(
										function() {
											vacationTypeService
													.deleteVacationType(id)
													.success(
															function(data) {
																toaster
																		.pop(
																				'success',
																				"Vacation type successfully deleted.");
																$scope.getAll();
															});
										}, function() {
										});
					};

					// Sort vacation types.
					$scope.sortBy = function(attribute) {
						if ($scope.sortOptions.field != attribute) {
							$scope.sortOptions = {
								field : attribute,
								direction : "asc"
							};
						} else {
							if ($scope.sortOptions.direction == "desc") {
								$scope.sortOptions.direction = "asc";
							} else {
								$scope.sortOptions.direction = "desc";
							}
						}
					};

					// Refresh vacation types when sort options change.
					$scope.$watch('sortOptions', function(newVal, oldVal) {
						if (newVal !== oldVal) {
							$scope.getAll();
						}
					}, true);

					// Refresh vacation types balances when vacation types
					// changes.
					$scope.$watch('vacationTypes', function(newVal, oldVal) {
						if (newVal !== oldVal) {
							$scope.getVacationTypesBalances();
						}
					}, true);

					// Go to create vacation type screen.
					$scope.goToCreateVacationType = function() {
						$location.path('/vacationTypes/create');
					};

					$scope.goToUpdateVacationType = function(id) {
						$location.path('vacationTypes/update/' + id);
					};

					// Get vacation types balances.
					$scope.getVacationTypesBalances = function() {
						$scope.numberOfVacations = new Array();
						$scope.vacationTypes.forEach(function(vacationType) {
							$scope.retrieveVacationCount(vacationType.id,
									vacationType.type,
									vacationType.numberOfDays);
						});
					};

					// Get vacation type balance for the given vacation type.
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

					if ($routeParams.vacationTypeId == null) {
						// initialization
						$scope.beginDate = new Date();
						$scope.endDate = new Date();
						$scope.dateFormat = 'dd/MM/yyyy';
						$scope.numberOfDays = 1;
						// sort
						$scope.sortOptions = {
							field : "type",
							direction : "asc"
						};
						// get all vacation types.
						$scope.getAll();
					} else {
						$scope.typeId = $routeParams.vacationTypeId;
						$scope.getVacationType();
					}
				});
