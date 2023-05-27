//
//  IngredientItem.swift
//  KMMeal
//
//  Created by Ali Taha Dinçer on 22.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import NukeUI
import shared

struct IngredientItem: View {
    
    @State
    var canImageLoaded = true
    
    @State
    var isChecked = false
    
    let model: IngredientModel
    
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
                }
                VStack(
                    alignment: .leading
                ) {
                    Text(model.name)
                        .fontWeight(.medium)
                    Text(model.count)
                }
            }
            Spacer()
            Button(
                action: {
                    isChecked.toggle()
                },
                label: {
                    Image(
                        systemName: isChecked ? "checkmark.square" : "square"
                    )
                    .resizable()
                    .frame(
                        width: 20,
                        height: 20
                    )
                }
            )
            .padding(.trailing)
        }
    }
}

struct IngredientItem_Previews: PreviewProvider {
    static let model = IngredientModel(
        name: "Beef",
        count: "1",
        imageUrl: ""
    )
    static var previews: some View {
        IngredientItem(
            model: model
        )
    }
}
