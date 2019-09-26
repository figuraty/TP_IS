// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ClientServer.proto

package com.dei.isassignment;

/**
 * Protobuf type {@code com.dei.isassignment.Request}
 */
public  final class Request extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.dei.isassignment.Request)
    RequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Request.newBuilder() to construct.
  private Request(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Request() {
    owners_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Request(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              owners_ = new java.util.ArrayList<com.dei.isassignment.Owner>();
              mutable_bitField0_ |= 0x00000001;
            }
            owners_.add(
                input.readMessage(com.dei.isassignment.Owner.parser(), extensionRegistry));
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        owners_ = java.util.Collections.unmodifiableList(owners_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.dei.isassignment.ClientServer.internal_static_com_dei_isassignment_Request_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.dei.isassignment.ClientServer.internal_static_com_dei_isassignment_Request_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.dei.isassignment.Request.class, com.dei.isassignment.Request.Builder.class);
  }

  public static final int OWNERS_FIELD_NUMBER = 1;
  private java.util.List<com.dei.isassignment.Owner> owners_;
  /**
   * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
   */
  public java.util.List<com.dei.isassignment.Owner> getOwnersList() {
    return owners_;
  }
  /**
   * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
   */
  public java.util.List<? extends com.dei.isassignment.OwnerOrBuilder> 
      getOwnersOrBuilderList() {
    return owners_;
  }
  /**
   * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
   */
  public int getOwnersCount() {
    return owners_.size();
  }
  /**
   * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
   */
  public com.dei.isassignment.Owner getOwners(int index) {
    return owners_.get(index);
  }
  /**
   * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
   */
  public com.dei.isassignment.OwnerOrBuilder getOwnersOrBuilder(
      int index) {
    return owners_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < owners_.size(); i++) {
      output.writeMessage(1, owners_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < owners_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, owners_.get(i));
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.dei.isassignment.Request)) {
      return super.equals(obj);
    }
    com.dei.isassignment.Request other = (com.dei.isassignment.Request) obj;

    boolean result = true;
    result = result && getOwnersList()
        .equals(other.getOwnersList());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getOwnersCount() > 0) {
      hash = (37 * hash) + OWNERS_FIELD_NUMBER;
      hash = (53 * hash) + getOwnersList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.dei.isassignment.Request parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.dei.isassignment.Request parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.dei.isassignment.Request parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.dei.isassignment.Request parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.dei.isassignment.Request parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.dei.isassignment.Request parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.dei.isassignment.Request parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.dei.isassignment.Request parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.dei.isassignment.Request parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.dei.isassignment.Request parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.dei.isassignment.Request parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.dei.isassignment.Request parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.dei.isassignment.Request prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.dei.isassignment.Request}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.dei.isassignment.Request)
      com.dei.isassignment.RequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.dei.isassignment.ClientServer.internal_static_com_dei_isassignment_Request_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.dei.isassignment.ClientServer.internal_static_com_dei_isassignment_Request_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.dei.isassignment.Request.class, com.dei.isassignment.Request.Builder.class);
    }

    // Construct using com.dei.isassignment.Request.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getOwnersFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (ownersBuilder_ == null) {
        owners_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        ownersBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.dei.isassignment.ClientServer.internal_static_com_dei_isassignment_Request_descriptor;
    }

    public com.dei.isassignment.Request getDefaultInstanceForType() {
      return com.dei.isassignment.Request.getDefaultInstance();
    }

    public com.dei.isassignment.Request build() {
      com.dei.isassignment.Request result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.dei.isassignment.Request buildPartial() {
      com.dei.isassignment.Request result = new com.dei.isassignment.Request(this);
      int from_bitField0_ = bitField0_;
      if (ownersBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          owners_ = java.util.Collections.unmodifiableList(owners_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.owners_ = owners_;
      } else {
        result.owners_ = ownersBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.dei.isassignment.Request) {
        return mergeFrom((com.dei.isassignment.Request)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.dei.isassignment.Request other) {
      if (other == com.dei.isassignment.Request.getDefaultInstance()) return this;
      if (ownersBuilder_ == null) {
        if (!other.owners_.isEmpty()) {
          if (owners_.isEmpty()) {
            owners_ = other.owners_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureOwnersIsMutable();
            owners_.addAll(other.owners_);
          }
          onChanged();
        }
      } else {
        if (!other.owners_.isEmpty()) {
          if (ownersBuilder_.isEmpty()) {
            ownersBuilder_.dispose();
            ownersBuilder_ = null;
            owners_ = other.owners_;
            bitField0_ = (bitField0_ & ~0x00000001);
            ownersBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getOwnersFieldBuilder() : null;
          } else {
            ownersBuilder_.addAllMessages(other.owners_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.dei.isassignment.Request parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.dei.isassignment.Request) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<com.dei.isassignment.Owner> owners_ =
      java.util.Collections.emptyList();
    private void ensureOwnersIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        owners_ = new java.util.ArrayList<com.dei.isassignment.Owner>(owners_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.dei.isassignment.Owner, com.dei.isassignment.Owner.Builder, com.dei.isassignment.OwnerOrBuilder> ownersBuilder_;

    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public java.util.List<com.dei.isassignment.Owner> getOwnersList() {
      if (ownersBuilder_ == null) {
        return java.util.Collections.unmodifiableList(owners_);
      } else {
        return ownersBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public int getOwnersCount() {
      if (ownersBuilder_ == null) {
        return owners_.size();
      } else {
        return ownersBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public com.dei.isassignment.Owner getOwners(int index) {
      if (ownersBuilder_ == null) {
        return owners_.get(index);
      } else {
        return ownersBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public Builder setOwners(
        int index, com.dei.isassignment.Owner value) {
      if (ownersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOwnersIsMutable();
        owners_.set(index, value);
        onChanged();
      } else {
        ownersBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public Builder setOwners(
        int index, com.dei.isassignment.Owner.Builder builderForValue) {
      if (ownersBuilder_ == null) {
        ensureOwnersIsMutable();
        owners_.set(index, builderForValue.build());
        onChanged();
      } else {
        ownersBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public Builder addOwners(com.dei.isassignment.Owner value) {
      if (ownersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOwnersIsMutable();
        owners_.add(value);
        onChanged();
      } else {
        ownersBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public Builder addOwners(
        int index, com.dei.isassignment.Owner value) {
      if (ownersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOwnersIsMutable();
        owners_.add(index, value);
        onChanged();
      } else {
        ownersBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public Builder addOwners(
        com.dei.isassignment.Owner.Builder builderForValue) {
      if (ownersBuilder_ == null) {
        ensureOwnersIsMutable();
        owners_.add(builderForValue.build());
        onChanged();
      } else {
        ownersBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public Builder addOwners(
        int index, com.dei.isassignment.Owner.Builder builderForValue) {
      if (ownersBuilder_ == null) {
        ensureOwnersIsMutable();
        owners_.add(index, builderForValue.build());
        onChanged();
      } else {
        ownersBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public Builder addAllOwners(
        java.lang.Iterable<? extends com.dei.isassignment.Owner> values) {
      if (ownersBuilder_ == null) {
        ensureOwnersIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, owners_);
        onChanged();
      } else {
        ownersBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public Builder clearOwners() {
      if (ownersBuilder_ == null) {
        owners_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        ownersBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public Builder removeOwners(int index) {
      if (ownersBuilder_ == null) {
        ensureOwnersIsMutable();
        owners_.remove(index);
        onChanged();
      } else {
        ownersBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public com.dei.isassignment.Owner.Builder getOwnersBuilder(
        int index) {
      return getOwnersFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public com.dei.isassignment.OwnerOrBuilder getOwnersOrBuilder(
        int index) {
      if (ownersBuilder_ == null) {
        return owners_.get(index);  } else {
        return ownersBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public java.util.List<? extends com.dei.isassignment.OwnerOrBuilder> 
         getOwnersOrBuilderList() {
      if (ownersBuilder_ != null) {
        return ownersBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(owners_);
      }
    }
    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public com.dei.isassignment.Owner.Builder addOwnersBuilder() {
      return getOwnersFieldBuilder().addBuilder(
          com.dei.isassignment.Owner.getDefaultInstance());
    }
    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public com.dei.isassignment.Owner.Builder addOwnersBuilder(
        int index) {
      return getOwnersFieldBuilder().addBuilder(
          index, com.dei.isassignment.Owner.getDefaultInstance());
    }
    /**
     * <code>repeated .com.dei.isassignment.Owner owners = 1;</code>
     */
    public java.util.List<com.dei.isassignment.Owner.Builder> 
         getOwnersBuilderList() {
      return getOwnersFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.dei.isassignment.Owner, com.dei.isassignment.Owner.Builder, com.dei.isassignment.OwnerOrBuilder> 
        getOwnersFieldBuilder() {
      if (ownersBuilder_ == null) {
        ownersBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.dei.isassignment.Owner, com.dei.isassignment.Owner.Builder, com.dei.isassignment.OwnerOrBuilder>(
                owners_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        owners_ = null;
      }
      return ownersBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.dei.isassignment.Request)
  }

  // @@protoc_insertion_point(class_scope:com.dei.isassignment.Request)
  private static final com.dei.isassignment.Request DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.dei.isassignment.Request();
  }

  public static com.dei.isassignment.Request getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Request>
      PARSER = new com.google.protobuf.AbstractParser<Request>() {
    public Request parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Request(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Request> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Request> getParserForType() {
    return PARSER;
  }

  public com.dei.isassignment.Request getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
