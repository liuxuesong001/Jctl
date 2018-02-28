package com.colud.jctl.bean;

/**
 * Created by Jcty on 2017/8/23.
 */

public class ChannelsBean extends BaseBean {


    /**
     * EasyDarwin : {"Body":{"ChannelName":"鍖椾含1鍙�","DeviceType":"ONVIF","URL":"rtmp://{host}:10935/hls/stream_1"},"Header":{"CSeq":"1","ErrorNum":"200","ErrorString":"Success OK","MessageType":"MSG_SC_SERVER_GET_CHANNEL_STREAM_ACK","Version":"v1"}}
     */

    private EasyDarwinBean EasyDarwin;

    public EasyDarwinBean getEasyDarwin() {
        return EasyDarwin;
    }

    public void setEasyDarwin(EasyDarwinBean EasyDarwin) {
        this.EasyDarwin = EasyDarwin;
    }

    public static class EasyDarwinBean {
        /**
         * Body : {"ChannelName":"鍖椾含1鍙�","DeviceType":"ONVIF","URL":"rtmp://{host}:10935/hls/stream_1"}
         * Header : {"CSeq":"1","ErrorNum":"200","ErrorString":"Success OK","MessageType":"MSG_SC_SERVER_GET_CHANNEL_STREAM_ACK","Version":"v1"}
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

        public static class BodyBean {
            /**
             * ChannelName : 鍖椾含1鍙�
             * DeviceType : ONVIF
             * URL : rtmp://{host}:10935/hls/stream_1
             */

            private String ChannelName;
            private String DeviceType;
            private String URL;

            public String getChannelName() {
                return ChannelName;
            }

            public void setChannelName(String ChannelName) {
                this.ChannelName = ChannelName;
            }

            public String getDeviceType() {
                return DeviceType;
            }

            public void setDeviceType(String DeviceType) {
                this.DeviceType = DeviceType;
            }

            public String getURL() {
                return URL;
            }

            public void setURL(String URL) {
                this.URL = URL;
            }
        }

        public static class HeaderBean {
            /**
             * CSeq : 1
             * ErrorNum : 200
             * ErrorString : Success OK
             * MessageType : MSG_SC_SERVER_GET_CHANNEL_STREAM_ACK
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
