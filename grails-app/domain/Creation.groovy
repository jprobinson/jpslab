class Creation {
	Date date
	String name
	String description
	String link
	static hasMany = [comments:CreationComment]
	
    static constraints = {
    }
}
