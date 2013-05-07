class Photo {
	String name
	String url
	String caption
	
    static constraints = {
		name(nullable:true)
		url(nullable:true)
    }
}
