class NewsService {

    boolean transactional = true
    
    def getNewsList = { params ->		
    	def max = params.max?.toInteger() ?: 5
    	def offset = params?.offset?.toInteger() ?: 0
    	News.createCriteria().list(max:max, offset:offset, sort:'date', order:'desc'){}
    }
    
    def findNewsDirectLink = { year, month, day, link ->
    	def newsDate = new GregorianCalendar(year,month-1,day,0,0)
		def newsDateEnd = new GregorianCalendar(year,month-1,day,23,59)
    	 
		News.findAll("from News where date between :newsDate and :newsEndDate and link like :newsLink",
				[newsDate:newsDate.getTime(),newsEndDate:newsDateEnd.getTime(),newsLink:link])
    }
}
