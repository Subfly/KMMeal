//
//  MealItem.swift
//  appiOS
//
//  Created by Ali Taha Dinçer on 20.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import NukeUI
import shared

struct MealItem: View {
    
    let model: MealModel
    
    @State
    var canImageLoaded = true
    
    var body: some View {
        VStack(
            alignment: .leading,
            spacing: 0
        ) {
            ZStack(
                alignment: .bottom
            ) {
                GeometryReader { proxy in
                    Color.gray.opacity(0.25)
                        .frame(
                            width: proxy.size.width,
                            height: proxy.size.width,
                            alignment: .center
                        )
                }
                if (canImageLoaded) {
                    LazyImage(url: URL(string: model.imageUrl)) { phase in
                        if let image = phase.image {
                            image
                                .resizable()
                                .aspectRatio(contentMode: .fill)
                        } else if let _ = phase.error {
                            EmptyView()
                                .task {
                                    canImageLoaded = false
                                }
                        } else {
                            ProgressView()
                        }
                    }
                }
            }
            
            Text(model.name)
                .font(.callout)
                .padding(.all, 6)
        }
        .background(
            Color.gray.opacity(0.25)
        )
        .cornerRadius(8)
    }
}

struct MealItem_Previews: PreviewProvider {
    static let model = MealModel(
        id: "123",
        name: "Beef",
        area: "Bay Area",
        category: "Beeg",
        imageUrl: "",
        drinkAlternative: "",
        ingredients: [],
        instructions: [],
        tags: [],
        videoUrl: ""
    )
    static var previews: some View {
        MealItem(model: model)
    }
}
