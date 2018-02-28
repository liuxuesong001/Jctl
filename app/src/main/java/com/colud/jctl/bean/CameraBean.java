package com.colud.jctl.bean;

import java.util.List;

/**
 * Created by Jcty on 2017/8/23.
 */

public class CameraBean extends BaseBean {


    /**
     * EasyDarwin : {"Body":{"ChannelCount":"2","Channels":[{"Channel":1,"Name":"鍖椾含1鍙�","Online":1,"SnapURL":"/snap/1/channel_1.jpg"},{"Channel":2,"Name":"鍖椾含2鍙�","Online":1,"SnapURL":"/snap/2/channel_2.jpg"}]},"Header":{"CSeq":"1","ErrorNum":"200","ErrorString":"Success OK","MessageType":"MSG_SC_SERVER_GET_CHANNELS_ACK","Version":"v1"}}
     */

    private EasyDarwinBean EasyDarwin;

    public EasyDarwinBean getEasyDarwin() {
        return EasyDarwin;
    }

    public void setEasyDarwin(EasyDarwinBean EasyDarwin) {
        this.EasyDarwin = EasyDarwin;
    }

    public static class EasyDarwinBean extends BaseBean {
        /**
         * Body : {"ChannelCount":"2","Channels":[{"Channel":1,"Name":"鍖椾含1鍙�","Online":1,"SnapURL":"/snap/1/channel_1.jpg"},{"Channel":2,"Name":"鍖椾含2鍙�","Online":1,"SnapURL":"/snap/2/channel_2.jpg"}]}
         * Header : {"CSeq":"1","ErrorNum":"200","ErrorString":"Success OK","MessageType":"MSG_SC_SERVER_GET_CHANNELS_ACK","Version":"v1"}
         */

        private BodyBean Body;
        private HeaderBean Header;

        public BodyBean getBody() {
            return Body;
        }

        public void setBody(BodyBean Body) {
            this.Body = Body;
        }

        public HeaderBean getHeader() {
            return Header;
        }

        public void setHeader(HeaderBean Header) {
            this.Header = Header;
        }

        public static class BodyBean extends BaseBean {
            /**
             * ChannelCount : 2
             * Channels : [{"Channel":1,"Name":"鍖椾含1鍙�","Online":1,"SnapURL":"/snap/1/channel_1.jpg"},{"Channel":2,"Name":"鍖椾含2鍙�","Online":1,"SnapURL":"/snap/2/channel_2.jpg"}]
             */

            private String ChannelCount;

            private List<ChannelsBean> Channels;

            public String getChannelCount() {
                return ChannelCount;
            }

            public void setChannelCount(String ChannelCount) {
                this.ChannelCount = ChannelCount;
            }

            public List<ChannelsBean> getChannels() {
                return Channels;
            }

            public void setChannels(List<ChannelsBean> Channels) {
                this.Channels = Channels;
            }

            public static class ChannelsBean extends BaseBean {
                /**
                 * Channel : 1
                 * Name : 鍖椾含1鍙�
                 * Online : 1
                 * SnapURL : /snap/1/channel_1.jpg
                 */

                private int Channel;
                private String Name;
                private int Online;
                private String SnapURL;

                public int getChannel() {
                    return Channel;
                }

                public void setChannel(int Channel) {
                    this.Channel = Channel;
                }

                public String getName() {
                    return Name;
                }

                public void setName(String Name) {
                    this.Name = Name;
                }

                public int getOnline() {
                    return Online;
                }

                public void setOnline(int Online) {
                    this.Online = Online;
                }

                public String getSnapURL() {
                    return SnapURL;
                }

                public void setSnapURL(String SnapURL) {
                    this.SnapURL = SnapURL;
                }
            }
        }

        public static class HeaderBean {
            /**
             * CSeq : 1
             * ErrorNum : 200
             * ErrorString : Success OK
             * MessageType : MSG_SC_SERVER_GET_CHANNELS_ACK
             * Version : v1
             */

            private String CSeq;
            private String ErrorNum;
            private String ErrorString;
            private String MessageType;
            private String Version;

            public String getCSeq() {
                return CSeq;
            }

            public void setCSeq(String CSeq) {
                this.CSeq = CSeq;
            }

            public String getErrorNum() {
                return ErrorNum;
            }

            public void setErrorNum(String ErrorNum) {
                this.ErrorNum = ErrorNum;
            }

            public String getErrorString() {
                return ErrorString;
            }

            public void setErrorString(String ErrorString) {
                this.ErrorString = ErrorString;
            }

            public String getMessageType() {
                return MessageType;
            }

            public void setMessageType(String MessageType) {
                this.MessageType = MessageType;
            }

            public String getVersion() {
                return Version;
            }

            public void setVersion(String Version) {
                this.Version = Version;
            }
        }
    }
}

