//sort filter
vacationAppFilters.filter('sortIconFilter', function() {
	return function(input, sortOptions) {
		var icon = "";
		if (sortOptions.field == input) {
			if ("desc" == sortOptions.direction) {
				icon = 'glyphicon glyphicon-sort-by-attributes-alt sortIcon';
			} else {
				icon = 'glyphicon glyphicon-sort-by-attributes sortIcon';
			}
		} else {
			icon = 'glyphicon glyphicon-sort sortIcon';
		}
		return icon;
	};
});