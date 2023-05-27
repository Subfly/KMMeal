//
//  CategoriesView.swift
//  appiOS
//
//  Created by Ali Taha Dinçer on 20.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import KMMViewModelSwiftUI
import shared

struct CategoriesView: View {
    
    @StateViewModel
    var stateMachine = CategoriesStateMachine()
    
    var body: some View {
        
        let state = stateMachine.uiState
        
        NavigationView {
            ZStack {
                if(state.isLoading) {
                    ProgressView()
                } else if (!state.error.isEmpty) {
                    OnErrorRetryView(
                        message: state.error,
                        action: {
                            stateMachine.onEvent(
                                event: CategoriesEvent.Refresh()
                            )
                        }
                    )
                } else {
                    ScrollView(showsIndicators: false) {
                        ForEach(state.data, id: \.id) { category in
                            NavigationLink(
                                destination: {
                                    CategoryDetailView(
                                        categoryName: category.name
                                    )
                                },
                                label: {
                                    CategoryItem(
                                        category: category
                                    )
                                }
                            )
                            .buttonStyle(PlainButtonStyle())
                            .padding(.horizontal, 18)
                        }
                    }
                }
            }
            .navigationTitle("Categories")
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    NavigationLink {
                        RandomMealView()
                    } label: {
                        Image(systemName: "dice")
                    }
                    .buttonStyle(.plain)
                }
            }
            .refreshable {
                stateMachine.onEvent(
                    event: CategoriesEvent.Refresh()
                )
            }
        }
    }
}

struct CategoriesView_Previews: PreviewProvider {
    static var previews: some View {
        CategoriesView()
    }
}
