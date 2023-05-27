//
//  MealDetailTitle.swift
//  KMMeal
//
//  Created by Ali Taha Dinçer on 22.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MealDetailTitle: View {
    let title: String
    var body: some View {
        VStack {
            Text(title)
                .font(.title)
                .fontWeight(.medium)
                .padding(.top)
            Divider()
        }
    }
}

struct MealDetailTitle_Previews: PreviewProvider {
    static var previews: some View {
        MealDetailTitle(
            title: "Beef"
        )
    }
}
