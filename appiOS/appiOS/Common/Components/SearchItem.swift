//
//  SearchItem.swift
//  KMMeal
//
//  Created by Ali Taha Dinçer on 21.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import NukeUI
import shared

struct SearchItem: View {
    @State
    var canImageLoaded = true
    
    let model: MealModel
    
    var body: some View {
        HStack {
            HStack {
                if(canImageLoaded) {
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
                    .frame(
                        width: 48,
                        height: 48
                    )
                    .clipShape(Circle())
                }
                Text(model.name)
            }
            Spacer()
            Image(systemName: "chevron.right")
        }
    }
}

struct SearchItem_Previews: PreviewProvider {
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
        SearchItem(
            model: model
        )
    }
}
