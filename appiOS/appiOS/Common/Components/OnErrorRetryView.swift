//
//  OnErrorRetryView.swift
//  KMMeal
//
//  Created by Ali Taha Dinçer on 22.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct OnErrorRetryView: View {
    
    let message: String
    let action: () -> Void
    
    var body: some View {
        VStack {
            Text(message)
                .padding(52)
                .multilineTextAlignment(.center)
            Button {
                action()
            } label: {
                HStack {
                    Text("Retry")
                    Image(systemName: "arrow.counterclockwise")
                }
            }
        }
    }
}

struct OnErrorRetryView_Previews: PreviewProvider {
    static var previews: some View {
        OnErrorRetryView(
            message: "Error Happened!",
            action: {
                print("Solved error...")
            }
        )
    }
}
