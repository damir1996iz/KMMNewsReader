//
//  NewsView.swift
//  iosApp
//
//  Created by SGcode SGcode on 25.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NewsView: View {
    var news:[News]
    
    var body: some View {
        ScrollView
        {
            VStack
            {
                ForEach(news, id:\.self)
                {
                    value in
                    NewsCard(news: value)
                        .padding()
                }
            }
        }
    }
}

struct NewsView_Previews: PreviewProvider {
    static var previews: some View {
        NewsView(news:[])
    }
}

struct NewsCard: View {
    var news:News
    
    var body: some View
    {
        ZStack
        {
            RoundedRectangle(cornerRadius: 5, style: .continuous)
                .fill(Color.white)
                .clipped()
                .shadow(radius: 4, x: 6, y: 7)
            VStack
            {
                AsyncImage(url: URL(string: news.imageUrl)) { image in
                    image.resizable()
                } placeholder: {
                    ProgressView()
                }
                .frame(height: 200)
                .padding()
                    
                Text(news.title)
                    .bold()
                    .padding()
                Text(news.content)
                    .padding()
                HStack
                {
                    
                }
            }
            .padding()
        }
    }
}
