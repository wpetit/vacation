// Create a controller with name VacationsCtrl to bind to the html page.
vacationAppControllers
		.controller('VacationsCtrl',
				function($scope, $location, toaster, vacationService,
						vacationTypeService) {

					// Initialization
					$scope.minDate = new Date();
					$scope.from = new Date();
					$scope.to = new Date();
					$scope.dateFormat = 'dd-MM-yyyy';
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

					// Save the vacation.
					$scope.save = function() {
						vacationService.save($scope.from, $scope.to,
								$scope.typeId).success(
								function(data) {
									$scope.from = null;
									$scope.to = null;
									toaster.pop('success',
											"Vacation successfully saved.");
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
					// Get vacations
					$scope.refresh();
					// Get vacation types balances
					$scope.retrieveVacationTypes();

				});
