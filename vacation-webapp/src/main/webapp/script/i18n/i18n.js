app.config(['$translateProvider', function ($translateProvider) {
  $translateProvider.translations('en', {
    '001': "The number of days of the vacation is higher than the number of days for vacation type.",
    '002': "The vacation is out of the vacation type period."
  });
 
  $translateProvider.translations('fr', {
    '001': "Le nombre de jours du congé est plus grand que le nombre de jours défini pour ce type de congé",
    '002': "La période de ces congés n'est pas incluses dans celle définie pour ce type de congé"
  });
 
  $translateProvider.preferredLanguage('en');
}]);