//
//  MealDetailHeaderSpecificatorRow.swift
//  KMMeal
//
//  Created by Ali Taha Dinçer on 21.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MealDetailHeaderSpecificatorItem: View {
    
    let iconSystemName: String
    let specificatorTitle: String
    let specifierValue: String
    
    var body: some View {
        HStack {
            Group {
                HStack {
                    Image(systemName: iconSystemName)
                    Text(specificatorTitle)
                }
                Spacer()
                Text(specifierValue)
            }
            .padding()
        }
        .background(
            Color.gray.opacity(0.25)
        )
        .cornerRadius(8)
    }
}

struct MealDetailHeaderSpecificatorItem_Previews: PreviewProvider {
    static let icon = "globe.europe.africa"
    static let title = "Area"
    static let value = "Turkey"
    static var previews: some View {
        MealDetailHeaderSpecificatorItem(
            iconSystemName: icon,
            specificatorTitle: title,
            specifierValue: value
        )
    }
}
