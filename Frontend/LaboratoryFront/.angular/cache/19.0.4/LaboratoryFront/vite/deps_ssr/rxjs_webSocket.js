import { createRequire } from 'module';const require = createRequire(import.meta.url);
import {
  require_Observable,
  require_ReplaySubject,
  require_Subject,
  require_Subscriber,
  require_Subscription
} from "./chunk-4R36J2VG.js";
import {
  __commonJS
} from "./chunk-YHCV7DAQ.js";

// node_modules/rxjs/dist/cjs/internal/observable/dom/WebSocketSubject.js
var require_WebSocketSubject = __commonJS({
  "node_modules/rxjs/dist/cjs/internal/observable/dom/WebSocketSubject.js"(exports) {
    "use strict";
    var __extends = exports && exports.__extends || /* @__PURE__ */ function() {
      var extendStatics = function(d, b) {
        extendStatics = Object.setPrototypeOf || {
          __proto__: []
        } instanceof Array && function(d2, b2) {
          d2.__proto__ = b2;
        } || function(d2, b2) {
          for (var p in b2) if (Object.prototype.hasOwnProperty.call(b2, p)) d2[p] = b2[p];
        };
        return extendStatics(d, b);
      };
      return function(d, b) {
        if (typeof b !== "function" && b !== null) throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() {
          this.constructor = d;
        }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
      };
    }();
    var __assign = exports && exports.__assign || function() {
      __assign = Object.assign || function(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
          s = arguments[i];
          for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p)) t[p] = s[p];
        }
        return t;
      };
      return __assign.apply(this, arguments);
    };
    Object.defineProperty(exports, "__esModule", {
      value: true
    });
    exports.WebSocketSubject = void 0;
    var Subject_1 = require_Subject();
    var Subscriber_1 = require_Subscriber();
    var Observable_1 = require_Observable();
    var Subscription_1 = require_Subscription();
    var ReplaySubject_1 = require_ReplaySubject();
    var DEFAULT_WEBSOCKET_CONFIG = {
      url: "",
      deserializer: function(e) {
        return JSON.parse(e.data);
      },
      serializer: function(value) {
        return JSON.stringify(value);
      }
    };
    var WEBSOCKETSUBJECT_INVALID_ERROR_OBJECT = "WebSocketSubject.error must be called with an object with an error code, and an optional reason: { code: number, reason: string }";
    var WebSocketSubject = function(_super) {
      __extends(WebSocketSubject2, _super);
      function WebSocketSubject2(urlConfigOrSource, destination) {
        var _this = _super.call(this) || this;
        _this._socket = null;
        if (urlConfigOrSource instanceof Observable_1.Observable) {
          _this.destination = destination;
          _this.source = urlConfigOrSource;
        } else {
          var config = _this._config = __assign({}, DEFAULT_WEBSOCKET_CONFIG);
          _this._output = new Subject_1.Subject();
          if (typeof urlConfigOrSource === "string") {
            config.url = urlConfigOrSource;
          } else {
            for (var key in urlConfigOrSource) {
              if (urlConfigOrSource.hasOwnProperty(key)) {
                config[key] = urlConfigOrSource[key];
              }
            }
          }
          if (!config.WebSocketCtor && WebSocket) {
            config.WebSocketCtor = WebSocket;
          } else if (!config.WebSocketCtor) {
            throw new Error("no WebSocket constructor can be found");
          }
          _this.destination = new ReplaySubject_1.ReplaySubject();
        }
        return _this;
      }
      WebSocketSubject2.prototype.lift = function(operator) {
        var sock = new WebSocketSubject2(this._config, this.destination);
        sock.operator = operator;
        sock.source = this;
        return sock;
      };
      WebSocketSubject2.prototype._resetState = function() {
        this._socket = null;
        if (!this.source) {
          this.destination = new ReplaySubject_1.ReplaySubject();
        }
        this._output = new Subject_1.Subject();
      };
      WebSocketSubject2.prototype.multiplex = function(subMsg, unsubMsg, messageFilter) {
        var self = this;
        return new Observable_1.Observable(function(observer) {
          try {
            self.next(subMsg());
          } catch (err) {
            observer.error(err);
          }
          var subscription = self.subscribe({
            next: function(x) {
              try {
                if (messageFilter(x)) {
                  observer.next(x);
                }
              } catch (err) {
                observer.error(err);
              }
            },
            error: function(err) {
              return observer.error(err);
            },
            complete: function() {
              return observer.complete();
            }
          });
          return function() {
            try {
              self.next(unsubMsg());
            } catch (err) {
              observer.error(err);
            }
            subscription.unsubscribe();
          };
        });
      };
      WebSocketSubject2.prototype._connectSocket = function() {
        var _this = this;
        var _a = this._config, WebSocketCtor = _a.WebSocketCtor, protocol = _a.protocol, url = _a.url, binaryType = _a.binaryType;
        var observer = this._output;
        var socket = null;
        try {
          socket = protocol ? new WebSocketCtor(url, protocol) : new WebSocketCtor(url);
          this._socket = socket;
          if (binaryType) {
            this._socket.binaryType = binaryType;
          }
        } catch (e) {
          observer.error(e);
          return;
        }
        var subscription = new Subscription_1.Subscription(function() {
          _this._socket = null;
          if (socket && socket.readyState === 1) {
            socket.close();
          }
        });
        socket.onopen = function(evt) {
          var _socket = _this._socket;
          if (!_socket) {
            socket.close();
            _this._resetState();
            return;
          }
          var openObserver = _this._config.openObserver;
          if (openObserver) {
            openObserver.next(evt);
          }
          var queue = _this.destination;
          _this.destination = Subscriber_1.Subscriber.create(function(x) {
            if (socket.readyState === 1) {
              try {
                var serializer = _this._config.serializer;
                socket.send(serializer(x));
              } catch (e) {
                _this.destination.error(e);
              }
            }
          }, function(err) {
            var closingObserver = _this._config.closingObserver;
            if (closingObserver) {
              closingObserver.next(void 0);
            }
            if (err && err.code) {
              socket.close(err.code, err.reason);
            } else {
              observer.error(new TypeError(WEBSOCKETSUBJECT_INVALID_ERROR_OBJECT));
            }
            _this._resetState();
          }, function() {
            var closingObserver = _this._config.closingObserver;
            if (closingObserver) {
              closingObserver.next(void 0);
            }
            socket.close();
            _this._resetState();
          });
          if (queue && queue instanceof ReplaySubject_1.ReplaySubject) {
            subscription.add(queue.subscribe(_this.destination));
          }
        };
        socket.onerror = function(e) {
          _this._resetState();
          observer.error(e);
        };
        socket.onclose = function(e) {
          if (socket === _this._socket) {
            _this._resetState();
          }
          var closeObserver = _this._config.closeObserver;
          if (closeObserver) {
            closeObserver.next(e);
          }
          if (e.wasClean) {
            observer.complete();
          } else {
            observer.error(e);
          }
        };
        socket.onmessage = function(e) {
          try {
            var deserializer = _this._config.deserializer;
            observer.next(deserializer(e));
          } catch (err) {
            observer.error(err);
          }
        };
      };
      WebSocketSubject2.prototype._subscribe = function(subscriber) {
        var _this = this;
        var source = this.source;
        if (source) {
          return source.subscribe(subscriber);
        }
        if (!this._socket) {
          this._connectSocket();
        }
        this._output.subscribe(subscriber);
        subscriber.add(function() {
          var _socket = _this._socket;
          if (_this._output.observers.length === 0) {
            if (_socket && (_socket.readyState === 1 || _socket.readyState === 0)) {
              _socket.close();
            }
            _this._resetState();
          }
        });
        return subscriber;
      };
      WebSocketSubject2.prototype.unsubscribe = function() {
        var _socket = this._socket;
        if (_socket && (_socket.readyState === 1 || _socket.readyState === 0)) {
          _socket.close();
        }
        this._resetState();
        _super.prototype.unsubscribe.call(this);
      };
      return WebSocketSubject2;
    }(Subject_1.AnonymousSubject);
    exports.WebSocketSubject = WebSocketSubject;
  }
});

// node_modules/rxjs/dist/cjs/internal/observable/dom/webSocket.js
var require_webSocket = __commonJS({
  "node_modules/rxjs/dist/cjs/internal/observable/dom/webSocket.js"(exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", {
      value: true
    });
    exports.webSocket = void 0;
    var WebSocketSubject_1 = require_WebSocketSubject();
    function webSocket(urlConfigOrSource) {
      return new WebSocketSubject_1.WebSocketSubject(urlConfigOrSource);
    }
    exports.webSocket = webSocket;
  }
});

// node_modules/rxjs/dist/cjs/webSocket/index.js
var require_webSocket2 = __commonJS({
  "node_modules/rxjs/dist/cjs/webSocket/index.js"(exports) {
    Object.defineProperty(exports, "__esModule", {
      value: true
    });
    exports.WebSocketSubject = exports.webSocket = void 0;
    var webSocket_1 = require_webSocket();
    Object.defineProperty(exports, "webSocket", {
      enumerable: true,
      get: function() {
        return webSocket_1.webSocket;
      }
    });
    var WebSocketSubject_1 = require_WebSocketSubject();
    Object.defineProperty(exports, "WebSocketSubject", {
      enumerable: true,
      get: function() {
        return WebSocketSubject_1.WebSocketSubject;
      }
    });
  }
});
export default require_webSocket2();
//# sourceMappingURL=rxjs_webSocket.js.map
