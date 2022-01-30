//
//  NewsView.swift
//  iosApp
//
//  Created by SGcode SGcode on 25.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import CachedAsyncImage
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
    
    let dateFormatter = DateFormatter()
    
    init(news: News)
    {
        self.news = news
        
        dateFormatter.dateFormat = "HH:mm dd.MM.yyyy"
    }
    
    var body: some View
    {
        VStack
        {
            CachedAsyncImage(url: URL(string: news.imageUrl)) { image in
                image.resizable().scaledToFit()
            } placeholder: {
                ProgressView()
            }
            .frame(height: 150, alignment: .center)
                
            Text(news.title)
                .bold()
            Text(news.content)
            HStack
            {
                ZStack
                {
                    RoundedRectangle(cornerRadius: 0.0)
                        .fill(Color.black)
                        .frame(width: 150.0, height: 30.0)
                    Text(news.author)
                        .font(.system(size: 16.0))
                        .foregroundColor(Color.white)
                        .padding()
                }
                Spacer()
                if(news.readMoreUrl?.isEmpty == false)
                {
                    let urlStr = (news.readMoreUrl ?? "").addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!
                    ZStack
                    {
                        RoundedRectangle(cornerRadius: 0.0)
                            .fill(Color.blue)
                            .frame(width: 100.0, height: 30.0)
                            Link(destination:URL(string: urlStr)!)
                            {
                                Text("Read more")
                                    .font(.system(size: 16.0))
                                    .foregroundColor(Color.white)
                                    .padding()
                            }
                    }
                }
            }
            .frame(height: 35.0)
            HStack
            {
                Text(dateFormatter.string(from: Date(timeIntervalSince1970: Double(news.timestamp))))
                    .foregroundColor(Color.gray)
                Spacer()
            }
        }
        .padding()
        .overlay(RoundedRectangle(cornerRadius: 5, style: .continuous)
                .stroke(Color(.sRGB, red: 150/255, green: 150/255, blue: 150/255, opacity: 0.1), lineWidth: 1.0))
    }
}
