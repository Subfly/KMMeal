//
//  SearchView.swift
//  appiOS
//
//  Created by Ali Taha Dinçer on 20.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import KMMViewModelSwiftUI
import shared

struct SearchView: View {
    
    @StateViewModel
    var searchStateMachine = SearchStateMachine()
    
    @State
    var queryHolder = ""
    @State
    var filterTypeHolder = SearchFilterType.none
    @State
    var showFiltersSheet = false
    
    var body: some View {
        NavigationView {
            VStack {
                Image(systemName: "magnifyingglass.circle")
                    .resizable()
                    .frame(
                        width: 100,
                        height: 100,
                        alignment: .center
                    )
                    .padding(.bottom)
                Text("Search some meals!")
                    .font(.title2)
                    .fontWeight(.medium)
                    .multilineTextAlignment(.center)
                    .padding()
                Text("Start typing to search the meal of your dreams")
                    .italic()
                    .multilineTextAlignment(.center)
                    .padding(.horizontal)
            }
            .padding()
            .frame(
                maxWidth: .infinity,
                maxHeight: .infinity
            )
            .opacity(0.5)
            .navigationTitle("Search")
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Image(systemName: "line.3.horizontal.decrease.circle")
                        .onTapGesture {
                            withAnimation {
                                showFiltersSheet = true
                            }
                        }
                }
            }
            .sheet(
                isPresented: $showFiltersSheet,
                onDismiss: {
                    withAnimation {
                        showFiltersSheet = false
                    }
                }
            ) {
                let filters = [
                    SearchFilterType.ingredient,
                    SearchFilterType.category,
                    SearchFilterType.area,
                    SearchFilterType.none,
                ]
                let labelText = "Apply a Filter"
                
                VStack(
                    alignment: .center
                ) {
                    Text(labelText)
                        .font(.body)
                        .fontWeight(.bold)
                        .padding()
                    Picker(
                        selection: $filterTypeHolder,
                        label: Text(labelText)
                    ) {
                        ForEach(filters, id: \.self) { filterType in
                            Text(filterType.displayName)
                        }
                    }
                    .pickerStyle(.wheel)
                }
                .onAppear {
                    filterTypeHolder = searchStateMachine.filterState
                }
                .presentationDetents([.fraction(0.25)])
            }
            .onChange(of: filterTypeHolder) { newValue in
                searchStateMachine.onEvent(
                    event: SearchEvent.OnFilterChanged(
                        newFilter: newValue
                    )
                )
            }
        }
        .searchable(text: $queryHolder) {
            let state = searchStateMachine.uiState
            
            if (state.isLoading) {
                ProgressView()
            } else if (!state.error.isEmpty) {
                Text(state.error)
                    .padding()
            } else if !queryHolder.isEmpty && state.data.isEmpty {
                
                let isFilterNone = filterTypeHolder == SearchFilterType.none
                
                Text("Nothing found containing ")
                + Text("\(queryHolder)").bold()
                + Text(isFilterNone ? "" : " with filter ")
                + Text(isFilterNone ? "" : "\(filterTypeHolder.displayName)").italic()
                
            } else if (!state.data.isEmpty) {
                ForEach(state.data, id: \.id) { meal in
                    NavigationLink {
                        MealDetailView(
                            mealId: meal.id,
                            mealName: meal.name
                        )
                    } label: {
                        SearchItem(model: meal)
                    }
                    .buttonStyle(.plain)
                }
            }
        
        }
        .onSubmit(of: .search) {
            searchStateMachine.onEvent(
                event: SearchEvent.OnQueryChanged(
                    newQuery: queryHolder
                )
            )
        }
        .onChange(of: queryHolder) { newValue in
            searchStateMachine.onEvent(
                event: SearchEvent.OnQueryChanged(
                    newQuery: newValue
                )
            )
        }
    }
}

struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView()
    }
}
