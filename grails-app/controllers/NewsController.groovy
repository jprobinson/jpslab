class NewsController {

	def index = {
			render(view:'/news/edit',model:[newsList : News.listOrderByDate(order:'desc')] )
	}
    def list = { 	
    		[newsList : News.listOrderByDate(order:'desc')] 
    }
    
    def edit = {
    		render(view:'/news/edit', model:[news:News.findById(params.id)])
    }
    
    def delete = {
    		def newsInstance = News.get(params.id)
    		newsInstance.delete()
    }
    
    def add = {
    		render(view:'/news/edit', model:[news:new News()])
    }
    
    def save = {
    		def newsInstance = News.get( params.id )
    		if(newsInstance==null){
    			newsInstance = new News(id:params.id,
    								text:params.text,
    								link:params.headline.replaceAll(/\s/,'_'),
    								headline:params.headline,
    								date:new Date())
    		}
            newsInstance.properties = params
            newsInstance.save()
    }
}
