class CreationService {

    boolean transactional = true
    
    def getCreationList = { params ->		
    	def max = params.max?.toInteger() ?: 5
    	def offset = params?.offset?.toInteger() ?: 0
    	
    	Creation.createCriteria().list(max:max, offset:offset, sort:'date', order:'desc'){ne('name','The Amazing Editable Box!')}
    }
    
    def findCreationDirectLink = { year, month, day, link ->
    	def creationDate = new GregorianCalendar(year,month-1,day,0,0)
		def creationDateEnd = new GregorianCalendar(year,month-1,day,23,59)
    	 
    	Creation.findAll("from Creation where name != 'The Amazing Editable Box!' and date between :creationDate and :creationEndDate and link like :creationLink",
				[creationDate:creationDate.getTime(),creationEndDate:creationDateEnd.getTime(),creationLink:link])
    }
}
