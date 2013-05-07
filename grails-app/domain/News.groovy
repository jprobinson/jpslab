class News {
	Date date
	String text
	String headline
	String link
	static hasMany = [comments:NewsComment]
	
    static constraints = {
    }
}
