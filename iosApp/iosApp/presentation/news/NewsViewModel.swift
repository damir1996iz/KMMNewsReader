//
//  NewsViewModel.swift
//  iosApp
//
//  Created by SGcode SGcode on 25.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class NewsViewModelWrapper : ObservableObject {
    @Published var news:NewsLoadingState = NewsLoadingState.Loading()
    @Published var newsType:NewsTypes = NewsTypes.cached
    
    private var newsViewModel = NewsViewModel()
    
    init() {
        newsViewModel.news.collect (collector: FlowCollector<NewsLoadingState>{
            v in
            self.news = v
        }, completionHandler: {
            a, b in
            
        })
        newsViewModel.currentNewsType.collect(collector: FlowCollector<NewsTypes>{
            v in
            self.newsType = v
        }, completionHandler: {
            a,b in
        })
    }
    
    func loadNews(type:NewsTypes)
    {
        newsViewModel.loadNews(type: type)
    }
}
