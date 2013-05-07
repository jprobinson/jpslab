class CreationComment {
	Date date
	String text
	String commentor
	static belongsTo = [creation:Creation]
	
    static constraints = {
    }
}
