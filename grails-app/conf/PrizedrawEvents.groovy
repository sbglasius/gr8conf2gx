import gr8conf2gx.PrizedrawEntry
events = {
    'afterInsert' browser:true, namespace:'gorm', filter:PrizedrawEntry
	'notifyClient' browser:true, namespace:  'gr8conf2gx'
	'sendConfirmMail' browser:true, namespace:  'gr8conf2gx'
}
