class NewsComment {
	Date date
	String text
	String commentor
	static belongsTo = [news:News]
	
    static constraints = {
    }
}
