# Welcome to TDD DOJO 1 - Photo-Archive-App
In this DOJO we want to create the backend for a small app, that should help the user to easily manage and view a photo archive.
On our way we want to train our TDD skills.

## Requirements
The main features of our app should be the following:

* Populate a photo index based on an existing image folder and keep the source folder and the index in sync.
* Browse photos form the index
* Assign any kind of labels to each photo
* Filter photos by any set of labels and time period
* Save and load executed filters

## Implementation-Process
### How to use User-Stories?
The User-Stories describe the features in a coarse grained wey.
Thy should be implemented test-driven and one after another.

Each User-Story itself is divided into sub-problems, that should also be solved step by step and test-driven.
This should help you to get used to the incremental process of TDD (test->code->refactor).

### What are Feature-Tests?
You'll write lots of small unit test (test-first) as you go on.
One of the benefits of TDD is, that the tests are describing the behavior of the software you're writing.
But all those fine-grained tests are really tltr;, so they aren't really a suitable documentation, even for programmers.

As an higher level of abstraction we want to document the code with so called "Feature-Tests".
These are special tests that assembles small subsets of units or modules (call them integration tests if you want to).
So as a result of each User-Story you should assemble the feature behind the story in a "Feature-Test".
This is the only test you'll write AFTER all components are ready to use. If you'd write this in a test-first manner,
your Feature-Test would fail until you finished the whole feature. The "Feature-Test" is a black box test and imho not
suitable for the TDD cycle. So at the end you'll write your "Feature-Test" and it should be instantly green.


## User-Stories

### Story 1: "As an admin I want to create an initial search index based on an image folder on the server"
We don't want to focus the client side UI, so we don't want to implement an upload feature in the first step.
We simply assume that there's an existing image folder on the server, that can be indexed right away.
The index should only contain the metadata of the source files including a relative path.

We start with this feature, because having an initial index is the prerequisite for all coming features down the way.
So this is one of the most basic feature we have.

1. Create a minmial domainobject (e.g. Asset) that represents an image resource in a search index.
1. Design a minimal interface for adding Assets to an empty search index and provide the most simple implementation for this interface.
1. Create a modul (e.g. AssetSource) that is able to create a list of Assets based on an image folder.
1. Assemble the feature in a "Feature-Test"

### Story 2: "As a user I want get a list of all indexed Assets"
After we have an initial search index we can start getting indexed items.
The most primitive search is getting ALL indexd items. So this is our next incremental step..

1. Create a minimal domain object (e.g. AssetList) that reprents a list of Assets.
1. Design a minimal interface for getting all assets from the search index and provide the most simple implementation for this interface.
1. Assemble the feature in a "Feature-Test"

### Story 3: "As a user I want to assign any kind of labels to each Asset"

1. Extend the domain. We need to have 1..n labels for each Asset.
1. Design a minimal interface for adding labels to already indexed Assets and provide the most simple implementation for this interface.
1. Assemble the feature in a "Feature-Test"

### Story 4: "As a user I want to get a list of Assets filtered by one or many labels (disjunction)"

1. ...
1. Assemble the feature in a "Feature-Test"

### Story 5: "As admin I want to keep an already indexed image folder in sync with the index."
Whenever an item appears in the image folder that is not indexed yet it should be indexed.
Whenever there are missing source files or broken URIs for index entries this entry should be marked as deleted or broken
but it should not be removed from the index permanently.

1. ...
1. Assemble the feature in a "Feature-Test"

### Story 6: "As user I want to see items that are marked as deleted or broken and I want to see a small thumbnail of the missing photo"

1. ...
1. Assemble the feature in a "Feature-Test"
