//
//  InstructionItem.swift
//  KMMeal
//
//  Created by Ali Taha Dinçer on 22.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct InstructionItem: View {
    
    @State
    var isChecked = false
    
    let step: Int
    let instruction: String
    
    var body: some View {
        HStack {
            HStack {
                ZStack {
                    Color.gray.opacity(0.5)
                        .frame(
                            width: 32,
                            height: 32
                        )
                        .clipShape(Circle())
                    Text("\(step)")
                }
                Text(instruction)
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

struct InstructionItem_Previews: PreviewProvider {
    static var previews: some View {
        InstructionItem(
            step: 1, instruction: "Make Beef"
        )
    }
}
