//
//  GridDetailView.swift
//  appiOS
//
//  Created by Ali Taha Dinçer on 20.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct GridDetailView: View {
    let data: 
    var body: some View {
        LazyVGrid(
            columns: [
                GridItem(),
                GridItem()
            ]
        ) {
            ForEach(currentState.data, id: \.id) { meal in
                AsyncImage(
                    url: URL(string: meal.imageUrl)
                ) { phase in
                    if let image = phase.image {
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                    } else if phase.error != nil {
                        Image(systemName: "photo")
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                    } else {
                        ProgressView()
                    }
                }
                .overlay(
                    alignment: .bottomLeading
                ) {
                    Text(meal.name)
                        .font(.caption)
                        .background(
                            Color.black.opacity(0.8)
                        )
                }
                .cornerRadius(8)
            }
        }
    }
}

struct GridDetailView_Previews: PreviewProvider {
    static var previews: some View {
        GridDetailView()
    }
}
